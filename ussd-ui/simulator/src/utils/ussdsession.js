let timer = null;

export function startSession(onExpire) {
  clearTimeout(timer);
  timer = setTimeout(() => {
    onExpire();
  }, 60000); // 60 sec timeout
}
