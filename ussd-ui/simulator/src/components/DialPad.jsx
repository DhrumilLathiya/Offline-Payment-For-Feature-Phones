import { useState } from "react";

function DialPad({ setScreen }) {
  const [code, setCode] = useState("");

  const press = (v) => {
    setCode((prev) => prev + v);
  };

  const reset = () => {
    setCode("");
  };

  const submit = () => {
    if (code === "*121#") {
      setScreen("menu");
    } else {
      alert("Invalid USSD Code");
    }
  };

  return (
    <div className="phone">
      <div className="screen">{code || "Enter USSD Code"}</div>

      <div className="keypad">
        {["1","2","3","4","5","6","7","8","9","*","0","#"].map((k) => (
          <button key={k} onClick={() => press(k)}>
            {k}
          </button>
        ))}
      </div>

      <div className="actions">
        <button onClick={submit}>OK</button>
        <button onClick={reset}>RESET</button>
      </div>
    </div>
  );
}

export default DialPad;
