function Menu({ setScreen }) {
  return (
    <div className="phone">
      <div className="screen">
        1. Register<br />
        2. Check Balance<br />
        3. Top Up<br />
        4. Send Money
      </div>

      <div className="keypad">
        <button onClick={() => setScreen("register")}>1</button>
        <button onClick={() => setScreen("balance")}>2</button>
        <button onClick={() => setScreen("topup")}>3</button>
        <button onClick={() => setScreen("send")}>4</button>
      </div>
    </div>
  );
}

export default Menu;
