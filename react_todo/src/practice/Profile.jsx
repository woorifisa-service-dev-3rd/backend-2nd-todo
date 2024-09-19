import AppBar from "./AppBar";
import AppMain from "./AppMain";
import { ChatBubbleOvalLeftIcon } from '@heroicons/react/24/outline'

export const Profile = () => {
  return (
    <div className="min-h-screen mx-4">
      <AppBar />
      <AppMain />
      <div>
        <ChatBubbleOvalLeftIcon width="24" height="24" className=""/>
      </div>
    </div>
  );
};
