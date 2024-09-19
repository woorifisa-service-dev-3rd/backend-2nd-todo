import React from "react";

function Sticker() {
  const { useState } = React;
  // 공감 수 카운트
  const [isCount, setIsCount] = useState(2134);

  // 버튼 클릭 시 호출되는 함수
  const handleClick = () => setIsCount(isCount + 1);
  return (
    <div className="flex items-center flex-col">
      <p>여행 가고 싶다!</p>
      <div className="flex rounded-3xl border-2 items-center justifiy-between w-24 h-12 justify-around p-1 bg-white">
        <span>{isCount?.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")}</span>
        <button className="border-[1px] shadow-md rounded-3xl p-1" onClick={handleClick}>
          {" "}
          <img
            src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Smilies/Red%20Heart.png"
            alt="Red Heart"
            width="25"
            height="25"
          />
        </button>
      </div>
    </div>
  );
}

export default Sticker;
