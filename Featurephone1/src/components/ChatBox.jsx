import { useState } from "react";
import { sendMessage } from "./api";
import "./ChatBox.css";

export default function ChatBox({ phone }) {
  const [messages, setMessages] = useState([
    { from: "bot", text: "Welcome!\nType: Bank Transaction" }
  ]);
  const [input, setInput] = useState("");
  const [online, setOnline] = useState(false);

  const send = async () => {
    if (!input.trim()) return;

    setMessages((prev) => [...prev, { from: "user", text: input }]);

    try {
      const res = await sendMessage(phone, input);
      setMessages((prev) => [...prev, { from: "bot", text: res.reply }]);
    } catch (err) {
      setMessages((prev) => [
        ...prev,
        { from: "bot", text: "❌ Server error. Try again." }
      ]);
    }

    setInput("");
  };

  // 🔹 Toggle handler (FIXED)
  const toggleSync = async () => {
    const newState = !online;
    setOnline(newState);

    if (newState) {
      try {
        await fetch("http://localhost:8080/user/sync", {
          method: "POST",
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify({
            phoneNo: phone   // ✅ phone from login page
          })
        });

        setMessages((prev) => [
          ...prev,
          { from: "bot", text: "✔ Wallet synced successfully" }
        ]);
      } catch (err) {
        setMessages((prev) => [
          ...prev,
          { from: "bot", text: "❌ Sync failed" }
        ]);
        setOnline(false); // rollback
      }
    }
  };

  return (
    <div className="phone-container">
      <div className="phone-header">
        📶 Feature Phone &nbsp; | &nbsp; {phone}
      </div>

      <div className="phone-screen">
        {messages.map((m, i) => (
          <div
            key={i}
            className={m.from === "bot" ? "msg-bot" : "msg-user"}
          >
            {m.from === "bot" ? "▶ " : "◀ "}
            {m.text}
          </div>
        ))}
      </div>

      {/* Online / Offline Toggle */}
      <div className="sync-toggle">
        <span className={`status ${online ? "on" : "off"}`}>
          {online ? "ONLINE" : "OFFLINE"}
        </span>

        <div
          className={`toggle-switch ${online ? "active" : ""}`}
          onClick={toggleSync}
        >
          <div className="toggle-knob"></div>
        </div>
      </div>

      <div className="phone-input">
        <input
          placeholder="Type reply..."
          value={input}
          onChange={(e) => setInput(e.target.value)}
          onKeyDown={(e) => e.key === "Enter" && send()}
        />
        <button onClick={send}>OK</button>
      </div>
    </div>
  );
}
