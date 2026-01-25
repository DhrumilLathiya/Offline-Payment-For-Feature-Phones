import { useState } from "react";
import Login from "./components/login";
import ChatBox from "./components/ChatBox";
import "./App.css";

export default function App() {
  const [phone, setPhone] = useState(null);

  return (
    <div className="app-layout">
      {!phone ? (
        <Login onLogin={setPhone} />
      ) : (
        <ChatBox phone={phone} />
      )}
    </div>
  );
}
