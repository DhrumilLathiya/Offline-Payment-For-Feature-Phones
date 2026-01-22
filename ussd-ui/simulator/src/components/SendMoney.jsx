import { useState } from "react";

function SendMoney({ setScreen }) {
  const [msg, setMsg] = useState("");

  return (
    <div className="phone">
      <div className="screen">
        Example:<br />
        PAY 120 9998887776
      </div>

      <input
        value={msg}
        onChange={(e) => setMsg(e.target.value)}
        placeholder="Enter command"
      />

      <button
        onClick={() => {
          alert("Payment Sent");
          setScreen("menu");
        }}
      >
        PAY
      </button>
    </div>
  );
}

export default SendMoney;
