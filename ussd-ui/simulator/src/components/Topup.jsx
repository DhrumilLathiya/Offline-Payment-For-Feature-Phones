import { useState } from "react";

function Topup({ setScreen }) {
  const [form, setForm] = useState({
    card: "",
    mobile: "",
    amount: "",
    pin: ""
  });

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const submit = () => {
    if (!/^\d{10}$/.test(form.mobile)) {
      alert("Mobile number must be exactly 10 digits");
      return;
    }

    if (form.pin.length === 0) {
      alert("Enter PIN");
      return;
    }

    alert("Top-Up Successful");
    setScreen("menu");
  };

  return (
    <div className="phone">
      <div className="screen">
        <b>Top-Up</b><br /><br />

        Debit Card Number:<br />
        <input
          name="card"
          value={form.card}
          onChange={handleChange}
        /><br />

        Mobile Number:<br />
        <input
          name="mobile"
          value={form.mobile}
          onChange={handleChange}
          placeholder="10-digit number"
        /><br />

        Amount:<br />
        <input
          name="amount"
          value={form.amount}
          onChange={handleChange}
        /><br />

        PIN:<br />
        <input
          type="password"
          name="pin"
          value={form.pin}
          onChange={handleChange}
        />
      </div>

      <button onClick={submit}>OK</button>
    </div>
  );
}

export default Topup;
