// src/services/socket.js
import { io } from "socket.io-client";

const socket = io("http://localhost:8080"); // your backend url

export default socket;
