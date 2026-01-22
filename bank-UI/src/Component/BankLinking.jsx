import { useState } from "react";

export default function BankLinking() {

  const [accountNumber, setAccountNumber] = useState("");
  const [debitCard, setDebitCard] = useState("");
  const [user, setUser] = useState(null);

  // Fetch account details from Dummy Bank
  const fetchAccount = () => {
    setUser({
      name: "Ramesh Kumar",
      phone: "98XXXXXX12",
      accountNumber: accountNumber,
    });
  };

  // Verify debit card + link wallet
  const verifyAndLink = () => {
    alert("✅ Debit Card Verified & Wallet Activated");
  };

  return (
    <div className="w-full max-w-lg bg-neutral-800 rounded-2xl shadow-xl p-8 text-white">
      {/* HEADER */}
      <div className="flex items-center gap-3 mb-6">
        <span className="text-2xl">🏦</span>
        <h2 className="text-xl font-semibold">
          Bank Assisted Wallet Linking
        </h2>
      </div>

      {/* ACCOUNT NUMBER */}
      <div className="mb-6">
        <label className="block text-sm text-gray-300 mb-2">
          Account Number
        </label>
        <div className="flex gap-2">
          <input
            type="text"
            placeholder="Enter Account Number"
            value={accountNumber}
            onChange={(e) => setAccountNumber(e.target.value)}
            className="flex-1 px-4 py-2 rounded-md bg-neutral-700 border border-neutral-600 focus:outline-none focus:ring-2 focus:ring-blue-500"
          />
          <button
            onClick={fetchAccount}
            className="px-4 py-2 bg-blue-600 rounded-md hover:bg-blue-700 transition"
          >
            Fetch
          </button>
        </div>
      </div>

      {/* USER DETAILS */}
      {user && (
        <>
          <div className="mb-6 p-4 rounded-lg bg-neutral-700 border border-neutral-600">
            <p className="text-sm mb-1">
              <span className="text-gray-400">Name:</span>{" "}
              <span className="font-medium">{user.name}</span>
            </p>
            <p className="text-sm mb-1">
              <span className="text-gray-400">Phone:</span>{" "}
              <span className="font-medium">{user.phone}</span>
            </p>
            <p className="text-sm">
              <span className="text-gray-400">Account:</span>{" "}
              <span className="font-medium">{user.accountNumber}</span>
            </p>
          </div>

          {/* DEBIT CARD */}
          <div className="mb-6">
            <label className="block text-sm text-gray-300 mb-2">
              Debit Card Number
            </label>
            <input
              type="text"
              placeholder="Enter Debit Card Number"
              value={debitCard}
              onChange={(e) => setDebitCard(e.target.value)}
              className="w-full px-4 py-2 rounded-md bg-neutral-700 border border-neutral-600 focus:outline-none focus:ring-2 focus:ring-green-500"
            />
          </div>

          <button
            onClick={verifyAndLink}
            className="w-full py-3 bg-green-600 rounded-lg font-medium hover:bg-green-700 transition"
          >
            Verify Card & Link Wallet
          </button>
        </>
      )}
    </div>
  );
}
