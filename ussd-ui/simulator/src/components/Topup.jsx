import { useState } from "react";

const steps = [
  "Enter Debit Card Number",
  "Enter Mobile Number",
  "Enter Amount"
];

function Topup({ setScreen }) {
  const [step, setStep] = useState(0);
  const [value, setValue] = useState("");

  const next = () => {
    if (step === steps.length - 1) {
      alert("Top Up Successful");
      setScreen("menu");
    } else {
      setStep(step + 1);
      setValue("");
    }
  };

  return (
    <div className="phone">
      <div className="screen">
        {steps[step]}<br />
        {value || "_"}
      </div>

      <div className="keypad">
        {[..."1234567890"].map((n) => (
          <button key={n} onClick={() => setValue(value + n)}>
            {n}
          </button>
        ))}
      </div>

      <button onClick={next}>OK</button>
    </div>
  );
}

export default Topup;
