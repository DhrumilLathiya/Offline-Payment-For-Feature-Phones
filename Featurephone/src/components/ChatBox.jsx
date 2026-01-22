// src/components/ChatBox.jsx
import React, { useEffect, useState } from "react";
import socket from "../services/socket";

const ChatBox = () => {

  const [messages, setMessages] = useState([
    { from: "bot", text: "Welcome!\nType: Bank Transaction" }
  ]);

  const [input, setInput] = useState("");

  useEffect(() => {

    socket.on("server-message", (msg) => {
      setMessages(prev => [...prev, { from: "bot", text: msg }]);
    });

    return () => socket.off("server-message");
  }, []);

  const sendMessage = () => {
    if (!input.trim()) return;

    setMessages(prev => [...prev, { from: "user", text: input }]);

    socket.emit("user-message", input); // send to backend
    setInput("");
  };

  return (
    <div className="phone">
      <div className="screen">

        {messages.map((m, i) => (
          <div key={i} className={m.from}>
            {m.text}
          </div>
        ))}

      </div>

      <div className="input-area">
        <input
          value={input}
          onChange={e => setInput(e.target.value)}
          placeholder="Type here..."
          onKeyPress={e => e.key === "Enter" && sendMessage()}
        />
        <button onClick={sendMessage}>Send</button>
      </div>
    </div>
  );
};

export default ChatBox;
