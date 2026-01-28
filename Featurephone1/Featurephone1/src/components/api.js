export async function sendMessage(phone, message) {
  const apiUrl = import.meta.env.VITE_API_URL || 'http://localhost:8080';
  const res = await fetch(`${apiUrl}/api/chat`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({
      phone,
      message
    })
  });
  return res.json();
}