<script setup>
import { ref } from 'vue';
import ChatMessages from './components/ChatMessages.vue'
import ChatInput from './components/ChatInput.vue'

const messages = ref([]);

const handleSendMessage = (msg) => {
  messages.value.push(msg);
  // 假设这里添加默认消息展示逻辑，例如添加一条默认消息
  if (messages.value.length === 0) {
    messages.value.push('欢迎使用聊天功能！');
  }
};
</script>

<template>
  <nav class="app-nav">
    <div class="nav-content">
      <h1>Freshman AI</h1>
    </div>
    <div class="nav-divider"></div>
  </nav>
  <div class="chat-container">
    <ChatMessages :messages="messages" />
    <ChatInput @send-message="handleSendMessage" />
  </div>
</template>

<style scoped>
/* 导航栏样式 */
.app-nav {
  background: linear-gradient(135deg, #1a1a2e 0%, #16213e 100%);
  color: #ffffff;
  padding: 12px 24px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  z-index: 1000;
  backdrop-filter: blur(10px);
  border-bottom: 1px solid rgba(255, 255, 255, 0.1);
}

.nav-content {
  display: flex;
  align-items: center;
  justify-content: space-between;
  max-width: 1200px;
  margin: 0 auto;
}

.nav-content h1 {
  font-size: 1.5rem;
  font-weight: 600;
  background: linear-gradient(90deg, #00dbde 0%, #fc00ff 100%);
  -webkit-background-clip: text;
  background-clip: text;
  color: transparent;
  margin: 0;
}

.nav-divider {
  height: 1px;
  background: linear-gradient(90deg, rgba(0,0,0,0) 0%, rgba(255,255,255,0.1) 50%, rgba(0,0,0,0) 100%);
  margin: 8px 0;
}

/* 聊天容器样式 */
.chat-container {
  margin-top: 72px;
  padding: 0;
  width: 800px;
  margin-left: auto;
  margin-right: auto;
  background-color: #f8f9fa;
  height: 86vh;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  padding-bottom: 20px;
  border-radius: 12px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
  overflow-y: auto;
}

/* 响应式布局调整保持不变 */
@media (max-width: 900px) {
  .chat-container {
    width: 95%;
    max-width: 800px;
    padding: 0 12px;
  }
}
</style>


