import { useState } from "react";

const steps = [
  "Enter Name",
  "Enter Mobile Number",
  "Enter Debit Card Number",
  "Enter PIN",
  "Confirm PIN"
];

function Register({ setScreen }) {
  const [step, setStep] = useState(0);
  const [value, setValue] = useState("");

  const next = () => {
    if (step === steps.length - 1) {
      alert("Registration Successful");
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
        {step >= 3 ? "*".repeat(value.length) : value || "_"}
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

export default Register;
