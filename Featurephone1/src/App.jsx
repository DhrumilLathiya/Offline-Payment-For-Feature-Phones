import { useState } from "react";
import Login from "./components/login";
import ChatBox from "./components/ChatBox";

export default function App() {
  const [phone, setPhone] = useState(null);

  return (
    <div className="h-full w-full bg-neutral-900 flex items-center justify-center">
      {!phone ? (
        <Login onLogin={setPhone} />
      ) : (
        <ChatBox phone={phone} />
      )}
    </div>
  );
}
