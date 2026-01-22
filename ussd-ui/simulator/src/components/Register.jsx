import { useState } from "react";

function Register({ setScreen }) {
  const [form, setForm] = useState({
    name: "",
    mobile: "",
    card: "",
    pin: "",
    confirmPin: ""
  });

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const submit = () => {
    if (form.pin !== form.confirmPin) {
      alert("PINs do not match");
      return;
    }
    alert("Registration Successful");
    setScreen("menu");
  };

  return (
    <div className="phone">
      <div className="screen">
        <b>Register</b><br /><br />

        Name:<br />
        <input
          name="name"
          value={form.name}
          onChange={handleChange}
        /><br />

        Mobile Number:<br />
        <input
          name="mobile"
          value={form.mobile}
          onChange={handleChange}
        /><br />

        Debit Card Number:<br />
        <input
          name="card"
          value={form.card}
          onChange={handleChange}
        /><br />

        PIN:<br />
        <input
          type="password"
          name="pin"
          value={form.pin}
          onChange={handleChange}
        /><br />

        Confirm PIN:<br />
        <input
          type="password"
          name="confirmPin"
          value={form.confirmPin}
          onChange={handleChange}
        />
      </div>

      <button onClick={submit}>OK</button>
    </div>
  );
}

export default Register;
