import { useState } from "react";
import "./Login.css";

export default function Login({ onLogin }) {
  const [phone, setPhone] = useState("");

  return (
    <div className="login-container">
      <div className="login-top">FEATURE PHONE</div>

      <div className="login-screen">
        <p>Enter Mobile Number</p>
        <input
          placeholder="98XXXXXXXX"
          value={phone}
          onChange={(e) => setPhone(e.target.value)}
        />
      </div>

      <button className="login-btn" onClick={() => onLogin(phone)}>
        START
      </button>
    </div>
  );
}
