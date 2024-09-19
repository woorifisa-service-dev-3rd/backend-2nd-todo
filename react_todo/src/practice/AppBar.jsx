// rafce
import React from "react";

const AppBar = () => {
  return (
    <div className="flex justify-between mx-2">
      <button>X</button>
      {/* ul>li*4 */}
      <ul>
        <li>user</li>
        <li>present</li>
        <li>send</li>
        <li>favorite</li>
      </ul>
    </div>
  );
};

export default AppBar;
