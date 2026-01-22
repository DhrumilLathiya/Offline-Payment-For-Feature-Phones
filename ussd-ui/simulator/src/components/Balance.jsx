function Balance({ setScreen }) {
  return (
    <div className="phone">
      <div className="screen">Your balance is ₹1240</div>
      <button onClick={() => setScreen("menu")}>OK</button>
    </div>
  );
}

export default Balance;
