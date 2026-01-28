# 📱 Offline SMS Wallet System  
### Secure Offline Payments with Server-to-Server JWT & Token-Based Authorization

---

## 📌 Overview

This project implements an **Offline SMS-Based Wallet System** that enables users to perform secure financial transactions **without internet access**.

It is designed for:
- Feature phones
- Low-connectivity / rural areas
- Offline-first payment use cases

The system uses **SMS for user interaction**, **PIN + one-time tokens for security**, and **JWT for secure server-to-server communication** with a bank system.

---

## 🧠 Key Design Principles

- Offline-first (SMS only for users)
- No internet required on user side
- No JWT over SMS
- Stateful, server-controlled security
- Clear separation of trust boundaries

---

## 🏗️ High-Level Architecture

```
<img width="1045" height="800" alt="check bank balance flow" src="https://github.com/user-attachments/assets/481ec99e-1a6b-4475-82f7-00130a399994" />
<img width="8192" height="6398" alt="final sms payment system" src="https://github.com/user-attachments/assets/8de49e40-d308-4a36-926a-31f4f14392a7" />
<img width="1318" height="1719" alt="Registration flow" src="https://github.com/user-attachments/assets/254429b5-488a-424f-a6e0-7de300ee1983" />
<img width="1147" height="2084" alt="reset pin flow" src="https://github.com/user-attachments/assets/9636ca99-b052-4bbc-aa18-4572242c1c79" />
<img width="1417" height="1457" alt="sequence of money transfer flow" src="https://github.com/user-attachments/assets/390a1e20-26c5-4c8d-8993-30c97b5babc6" />
<img width="1383" height="1269" alt="top up flow" src="https://github.com/user-attachments/assets/15f06ef9-3de0-409b-8ae0-8b02e5e81f4c" />
<img width="1623" height="936" alt="Wallet Bank Settlement Flow" src="https://github.com/user-attachments/assets/d0975b70-6d99-4d12-967a-ea52229bcfca" />

```

---

## 🔐 Security Architecture

### 1️⃣ User → Backend Security (Offline SMS)

Because SMS is **insecure and replayable**, JWT is **not used** here.

Security mechanisms:
- Wallet PIN (authentication)
- Server-side session state
- One-time payment tokens
- PIN attempt limits

This protects against:
- Replay attacks
- Stolen phone misuse
- Brute-force PIN attacks
- Message tampering

---

### 2️⃣ Backend → Bank Security (Online)

Both servers are always online, so **JWT is used correctly here**.

Security mechanisms:
- JWT (HMAC / HS256)
- HTTPS
- Short-lived tokens
- Scoped authorization

This protects against:
- Fake settlement requests
- Server impersonation
- Unauthorized bank operations

---

## 🔑 Authentication & Authorization Flow

### 🟢 Registration
1. User sends SMS
2. Debit card number is verified
3. Debit card PIN is verified
4. Wallet PIN is set
5. Wallet is created

---

### 💸 Money Transfer (Offline)
1. User enters amount
2. User enters receiver mobile number
3. User enters wallet PIN
4. Backend:
   - Verifies PIN
   - Deducts balance
   - Generates a PaymentToken
5. Transaction remains **PENDING**

---

### 🔄 Bank Synchronization (Online)
1. Backend collects pending tokens
2. Backend generates JWT
3. Backend sends request to Bank Server
4. Bank verifies JWT
5. Bank responds with SUCCESS or FAILED
6. Backend updates wallet and token status

---

## 🔐 Why Two Different Security Models?

| Layer | Internet | Security Used | Reason |
|-----|---------|---------------|-------|
User → Backend | ❌ No | PIN + One-Time Token | SMS is insecure |
Backend → Bank | ✅ Yes | JWT | Servers are online |

> JWT is intentionally **NOT used over SMS**.

---

## 🧪 Major Attacks & Mitigations

| Attack | Mitigation |
|------|-----------|
Replay Attack | One-time tokens |
Fake Token | Server-side validation |
Brute-force PIN | Attempt limit |
Stolen Phone | PIN required |
Double Spend | Token-based settlement |
Fake Bank Server | JWT verification |
Unauthorized Settlement | JWT scope & expiry |

---

## 📂 Important Modules

### 🔴 Security-Critical Services
- `ChatService` – SMS entry point & routing
- `RegisterService` – KYC & wallet creation
- `WalletService` – PIN verification & transactions
- `TokenService` – Payment token generation
- `WalletSyncService` – Bank settlement with JWT

---

## 📦 Core Entities

- `Wallet` – User wallet
- `UserSession` – Server-side state machine
- `PaymentToken` – Pending transaction record

---

## 🔧 Technologies Used

- Java 17
- Spring Boot
- Spring Data JPA
- RESTTemplate
- JWT (jjwt)
- MySQL / H2
- Lombok

---

## 🚀 How to Run

### Backend Server
```bash
git clone <your-repo-url>
cd offlinebackend
mvn spring-boot:run
```

### Bank Server
```bash
cd dummy-bank
mvn spring-boot:run
```

---

## 🏆 Why This Project Is Strong

- Solves a real-world offline payment problem
- Uses correct security models
- Avoids misuse of JWT
- Implements defense-in-depth
- Designed like a real banking system

---

## 📌 Future Improvements

- Token expiry & one-time enforcement
- Atomic DB transactions (double-spend prevention)
- RSA-based JWT (RS256)
- SIM-binding (ICCID)
- Fraud detection rules

---



## 📜 License

MIT License
