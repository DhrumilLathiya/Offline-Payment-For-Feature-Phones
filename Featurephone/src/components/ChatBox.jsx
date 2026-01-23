import { useState } from "react";
import { sendMessage } from "./api";

export default function ChatBox({ phone }) {
  const [messages, setMessages] = useState([
    { from: "bot", text: "Welcome!\nType: Bank Transaction" }
  ]);
  const [input, setInput] = useState("");

  const send = async () => {
    if (!input.trim()) return;

    setMessages((prev) => [...prev, { from: "user", text: input }]);

    const res = await sendMessage(phone, input);

    setMessages((prev) => [
      ...prev,
      { from: "bot", text: res.reply }
    ]);

    setInput("");
  };

  return (
    <div className="w-[360px] h-[640px] bg-black rounded-3xl border-4 border-neutral-700 flex flex-col text-white shadow-2xl">

      <div className="flex-1 p-4 overflow-y-auto space-y-2 text-sm">
        {messages.map((m, i) => (
          <div
            key={i}
            className={`max-w-[80%] px-3 py-2 rounded-lg whitespace-pre-line ${
              m.from === "bot"
                ? "bg-neutral-700 self-start"
                : "bg-green-600 self-end ml-auto"
            }`}
          >
            {m.text}
          </div>
        ))}
      </div>

      <div className="p-3 border-t border-neutral-700 flex gap-2">
        <input
          className="flex-1 px-3 py-2 bg-neutral-800 rounded-md outline-none"
          placeholder="Type here..."
          value={input}
          onChange={(e) => setInput(e.target.value)}
          onKeyDown={(e) => e.key === "Enter" && send()}
        />
        <button
          onClick={send}
          className="bg-blue-600 px-4 rounded-md hover:bg-blue-700"
        >
          Send
        </button>
      </div>

    </div>
  );
}
