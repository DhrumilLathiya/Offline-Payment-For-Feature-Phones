export async function sendMessage(phone, message) {
  const res = await fetch("http://localhost:8080/api/chat", {
    method: "POST",
    headers:{
      "Content-Type": "application/json"
    },
    body: JSON.stringify({
      phone,
      message
    })
  });
  return res.json();
}