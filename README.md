// server.js
const express = require("express");
const http = require("http");
const socketIO = require("socket.io");

const app = express();
const server = http.createServer(app);
const io = socketIO(server, {
  cors: {
    origin: "*", // اجازه اتصال از همه کلاینت‌ها
    methods: ["GET", "POST"]
  }
});

io.on("connection", (socket) => {
  console.log("کاربر وصل شد:", socket.id);

  // دریافت و ارسال Offer
  socket.on("offer", (data) => {
    socket.broadcast.emit("offer", data);
  });

  // دریافت و ارسال Answer
  socket.on("answer", (data) => {
    socket.broadcast.emit("answer", data);
  });

  // دریافت و ارسال Candidate
  socket.on("candidate", (data) => {
    socket.broadcast.emit("candidate", data);
  });

  socket.on("disconnect", () => {
    console.log("کاربر قطع شد:", socket.id);
  });
});

server.listen(3000, () => {
  console.log("سرور سیگنالینگ روی پورت 3000 اجرا شد");
});
