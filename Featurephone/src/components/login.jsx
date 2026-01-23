import { useState } from "react";

export default function Login({ onLogin }) {
  const [phone, setPhone] = useState("");

  return (
    <div className="bg-neutral-800 p-8 rounded-xl w-[350px] text-white shadow-xl">
      <h2 className="text-xl font-semibold mb-4 text-center">
        📱 Enter Phone Number
      </h2>

      <input
        className="w-full px-4 py-2 mb-4 bg-neutral-700 rounded-md outline-none"
        placeholder="98XXXXXXXX"
        value={phone}
        onChange={(e) => setPhone(e.target.value)}
      />

      <button
        onClick={() => onLogin(phone)}
        className="w-full bg-blue-600 py-2 rounded-md hover:bg-blue-700"
      >
        Start
      </button>
    </div>
  );
}
