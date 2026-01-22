import { useState } from "react";
import DialPad from "./components/DialPad";
import Menu from "./components/Menu";
import Register from "./components/Register";
import Balance from "./components/Balance";
import Topup from "./components/Topup";
import SendMoney from "./components/SendMoney";

function App() {
  const [screen, setScreen] = useState("dial");

  return (
    <>
      {screen === "dial" && <DialPad setScreen={setScreen} />}
      {screen === "menu" && <Menu setScreen={setScreen} />}
      {screen === "register" && <Register setScreen={setScreen} />}
      {screen === "balance" && <Balance setScreen={setScreen} />}
      {screen === "topup" && <Topup setScreen={setScreen} />}
      {screen === "send" && <SendMoney setScreen={setScreen} />}
    </>
  );
}

export default App;
