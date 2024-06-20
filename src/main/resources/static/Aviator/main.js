const getSignal = document.getElementById("get-signal");
const stopSignalTimeBlock = document.getElementById("stop-signal-time-block");
const printSignal = document.getElementById("print-signal");
const stopProgress = document.getElementById("stop-progress");

//Функция для генерации случайного числа
function getRandomFloat(min, max, decimals) {
  const str = (Math.random() * (max - min) + min).toFixed(
    decimals,
  );
  return parseFloat(str);
}

//Нажатие на кнопку "GET SIGNAL"
getSignal.onclick = function () {
  let receivingSignal = getRandomFloat(1.00, 15.99, 2);

  if ((receivingSignal.toString().length == 3)) {
    receivingSignal += "0";
  } if ((receivingSignal.toString().length == 1)) {
    receivingSignal += ".00";
  }
  printSignal.innerHTML = `${receivingSignal}${"x"}`;
  printSignal.classList.remove("deactivate");
  goTimer(20);
  getSignal.disabled = true;
}

//Таймер после получения сигнала
function goTimer(time) {
  const timer = setInterval(() => {
    if (time >= 1) {
      getSignal.classList.add("deactivate");
      getSignal.style["z-index"] = "-1";
      stopProgress.style["animation"] = "animateProgress 20s linear infinite";
      stopSignalTimeBlock.classList.remove("deactivate");
      let stopTimer = document.getElementById("stop-timer");
      stopTimer.innerHTML = `${time--}${"<span> seconds</span>"}`;
      timerr = time;
      getSignal.disabled = true;
    } else {
      getSignal.classList.remove("deactivate");
      getSignal.style["z-index"] = "5";
      stopSignalTimeBlock.classList.add("deactivate");
      stopProgress.style["animation"] = "none";
      clearInterval(timer);
      getSignal.disabled = false;
    }
  }, 1000)
}