export function saveOffline(txn) {
  let q = JSON.parse(localStorage.getItem("offlineQueue")) || [];
  q.push(txn);
  localStorage.setItem("offlineQueue", JSON.stringify(q));
}
