import React from 'react'

const IconButton = ({ onClick, textColor, icon }) => {
  return (
    <button onClick={onClick} className={`w-8 text-xl font-semibold cursor-pointer ${textColor}`}>
        {icon}
    </button>
  )
}

export default IconButton