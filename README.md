# 📱 Offline SMS Wallet System  
### Secure Offline Payments with Token-Based Authorization & Server-to-Server JWT

---

## 📌 Overview

This project implements an **Offline SMS-Based Wallet System** that enables users to perform secure financial transactions **without internet access**.

It is designed for:
- 📞 Feature phones  
- 🌐 Low-connectivity / rural areas  
- 📴 Offline-first payment use cases  

The system uses:
- **SMS** for user interaction  
- **Wallet PIN + one-time tokens** for security  
- **JWT only for server-to-server communication** with the bank  

---

## 🧠 Key Design Principles

- Offline-first (SMS only for users)
- No internet required on user side
- ❌ No JWT over SMS
- Stateful, server-controlled security
- Clear separation of trust boundaries

---

## 🏗️ High-Level Architecture

### 🔹 Final System Architecture
![Final SMS Payment System](docs/docs/images/final_sms_payment_system.png)

### 🔹 Registration Flow
![Registration Flow](docs/images/registration_flow.png)

### 🔹 Check Bank Balance Flow
![Check Bank Balance Flow](docs/images/check_bank_balance_flow.png)

### 🔹 Reset PIN Flow
![Reset PIN Flow](docs/images/reset_pin_flow.png)

### 🔹 Money Transfer Flow
![Money Transfer Flow](docs/images/money_transfer_flow.png)

### 🔹 Top-Up Flow
![Top-Up Flow](docs/images/top_up_flow.png)

### 🔹 Wallet–Bank Settlement Flow
![Wallet Bank Settlement Flow](docs/images/wallet_bank_settlement_flow.png)


---

## 🔐 Security Architecture

### 1️⃣ User → Backend Security (Offline SMS)

Since SMS is **insecure and replayable**, **JWT is NOT used** here.

Security mechanisms:
- Wallet PIN authentication
- Server-side session tracking
- One-time payment tokens
- PIN attempt limits

Protects against:
- Replay attacks
- Stolen phone misuse
- Brute-force PIN attempts
- Message tampering

---

### 2️⃣ Backend → Bank Security (Online)

Both servers are online → **JWT is used correctly**.

Security mechanisms:
- JWT (HS256)
- HTTPS
- Short-lived tokens
- Scoped authorization

Protects against:
- Fake settlement requests
- Server impersonation
- Unauthorized bank operations

---

## 🔑 Authentication & Authorization Flow

### 🟢 Registration
1. User sends SMS  
2. Debit card number verification  
3. Debit card PIN verification  
4. Wallet PIN setup  
5. Wallet creation  

---

### 💸 Money Transfer (Offline)
1. User enters amount  
2. User enters receiver mobile number  
3. User enters wallet PIN  
4. Backend:
   - Verifies PIN  
   - Deducts balance  
   - Generates `PaymentToken`  
5. Transaction marked **PENDING**

---

### 🔄 Bank Synchronization (Online)
1. Backend collects pending tokens  
2. Backend generates JWT  
3. Backend sends request to Bank Server  
4. Bank verifies JWT  
5. Bank responds (SUCCESS / FAILED)  
6. Backend updates wallet & token status  

---

## 🔐 Why Two Different Security Models?

| Layer | Internet | Security Used | Reason |
|-----|---------|---------------|-------|
User → Backend | ❌ No | PIN + One-Time Token | SMS is insecure |
Backend → Bank | ✅ Yes | JWT | Trusted online servers |

> ⚠️ JWT is **intentionally NOT used over SMS**

---

## 🧪 Major Attacks & Mitigations

| Attack | Mitigation |
|------|-----------|
Replay Attack | One-time tokens |
Fake Token | Server-side validation |
Brute-force PIN | Attempt limit |
Stolen Phone | Wallet PIN |
Double Spend | Token-based settlement |
Fake Bank Server | JWT verification |
Unauthorized Settlement | JWT scope & expiry |

---

## 📂 Important Modules

### 🔴 Security-Critical Services
- `ChatService` – SMS entry & routing  
- `RegisterService` – KYC & wallet creation  
- `WalletService` – PIN verification & transactions  
- `TokenService` – Payment token generation  
- `WalletSyncService` – Bank settlement using JWT  

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

### ▶ Backend Server
```bash
git clone <your-repo-url>
cd offlinebackend
mvn spring-boot:run
```
▶ Bank Server
```bash
cd dummy-bank
mvn spring-boot:run
```
🏆 Why This Project Is Strong

Solves a real-world offline payment problem
Uses correct security models
Avoids JWT misuse
Implements defense-in-depth
Designed like a real banking system

📌 Future Improvements

Token expiry enforcement
Atomic DB transactions
RSA-based JWT (RS256)
SIM binding (ICCID)
Fraud detection rules

📜 License

MIT License
