# Booking Service — EventForge Microservice

The **Booking Service** is a core **microservice** within the EventForge ecosystem responsible for managing **ticket reservations and booking lifecycle management**.

It handles the process of **creating, confirming, cancelling, and expiring bookings** while coordinating with the **Ticket Service** and **Payment Service**.

The Booking Service acts as the **reservation layer** of the system and ensures tickets are only issued **after successful payment**.

## Features

- **Booking Creation:** Reserve selected tickets temporarily.
- **Booking Lifecycle:** Handles `PENDING`, `CONFIRMED`, and `CANCELLED` states.
- **Payment Coordination:** Integrates with Payment Service to confirm bookings.
- **Ticket Issuance Trigger:** Calls Ticket Service after payment success.
- **Auto Expiration:** Cancels bookings if payment is not completed in time.

---

## Booking Flow

The booking lifecycle follows this process:

```
User selects tickets
        ↓
Booking is created (PENDING)
        ↓
Payment is attempted
        ↓
If payment succeeds → Booking CONFIRMED
If payment fails → Booking CANCELLED
```

Important rule:

**Tickets are NOT created when booking is created.**

Tickets are issued **only after successful payment confirmation**.

## Service Interaction

The Booking Service coordinates with other microservices in the system.

```
User → Booking Service
Booking Service → Ticket Service (validate ticket availability)
Booking Service → creates PENDING booking

User → Payment Service
Payment Service → Booking Service (confirm booking)

Booking Service → Ticket Service (issue tickets)
```

## Database Design

The Booking Service database stores **booking reservations and booking items**.

Key entities:

- **Booking**
    - Stores booking metadata such as user, event, status, and total price.

- **BookingItem**
    - Stores ticket type selections and quantity.

- **BookingStatus**
    - Represents lifecycle states of a booking.

```
PENDING
CONFIRMED
CANCELLED
```

---

## Tech Stack

- **Backend:** Node.js / Express
- **Database:** PostgreSQL
- **ORM:** Prisma
- **Caching / Queue (optional):** Redis
- **Containerization:** Docker
- **Deployment:** Dockerized microservice setup

## API Endpoints

### Bookings

| Method   | Endpoint                         | Description                       |
| -------- | -------------------------------- | --------------------------------- |
| **POST** | `/bookings`                      | Create a new booking              |
| **GET**  | `/bookings/{booking_id}`         | Retrieve booking details          |
| **POST** | `/bookings/{booking_id}/cancel`  | Cancel a booking                  |
| **POST** | `/bookings/{booking_id}/confirm` | Confirm booking after payment     |
| **POST** | `/bookings/{booking_id}/expire`  | Expire booking (internal process) |

---

## Create Booking

```
POST /bookings
```

Request

```json
{
    "userId": "uuid",
    "eventId": "uuid",
    "items": [
        {
            "ticketTypeId": "uuid",
            "quantity": 2
        },
        {
            "ticketTypeId": "uuid",
            "quantity": 1
        }
    ]
}
```

Internal flow:

```
1 validate event exists
2 validate ticket types belong to event
3 check ticket availability from Ticket Service
4 calculate total price
5 create booking with status PENDING
6 create booking items
7 set expiration time
```

Response

```json
{
    "bookingId": "uuid",
    "status": "PENDING",
    "totalPrice": 4500,
    "expiresAt": "2026-03-07T14:00:00Z"
}
```

Bookings typically expire in **10–15 minutes** if payment is not completed.

---

## Get Booking

```
GET /bookings/{bookingId}
```

Response

```json
{
    "id": "uuid",
    "userId": "uuid",
    "eventId": "uuid",
    "status": "PENDING",
    "totalPrice": 4500,
    "items": [
        {
            "ticketTypeId": "uuid",
            "quantity": 2,
            "pricePerTicket": 2000
        },
        {
            "ticketTypeId": "uuid",
            "quantity": 1,
            "pricePerTicket": 500
        }
    ],
    "createdAt": "timestamp"
}
```

---

## Cancel Booking

User can cancel the booking manually.

```
POST /bookings/{bookingId}/cancel
```

Rules:

```
Only bookings with status PENDING can be cancelled.
```

Response

```json
{
    "bookingId": "uuid",
    "status": "CANCELLED"
}
```

---

## Confirm Booking (Payment Success)

Called by **Payment Service** when payment succeeds.

```
POST /bookings/{bookingId}/confirm
```

Flow:

```
1 validate booking exists
2 ensure booking status = PENDING
3 update booking status to CONFIRMED
4 call Ticket Service to issue tickets
```

Response

```json
{
    "bookingId": "uuid",
    "status": "CONFIRMED"
}
```

---

## Expire Booking

If payment is not completed within the allowed time window, the booking expires automatically.

Handled via:

```
cron job
queue worker
background scheduler
```

Endpoint (internal use):

```
POST /bookings/{bookingId}/expire
```

Response

```json
{
    "bookingId": "uuid",
    "status": "CANCELLED"
}
```

## Role in EventForge Architecture

The **Booking Service** acts as the **reservation coordinator** between users, payment processing, and ticket issuance.

```
Booking Service =
Ticket reservation + Payment coordination
```

It ensures:

- Tickets are not oversold
- Payments are validated before ticket issuance
- Temporary reservations expire automatically

---

## 👨‍💻 Author

- [@Mukul Daroch](https://github.com/mukuldaroch)
