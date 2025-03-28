<script setup>
import { ref, watch, nextTick } from 'vue';
import { marked } from 'marked';
import DOMPurify from 'dompurify';

// 配置marked
marked.setOptions({
  mangle: false,
  headerIds: false,
  breaks: true
});

const preprocessContent = (content) => {
  if (!content.startsWith('<')) {
    return content.replace(/\n/g, '\n\n');
  }
  return content;
};

const props = defineProps({
  messages: {
    type: Array,
    default: () => []
  }
});

const messagesContainer = ref(null);

watch(() => props.messages, async () => {
  await nextTick();
  messagesContainer.value.scrollTop = messagesContainer.value.scrollHeight;
}, { deep: true });
</script>

<template>
  <div ref="messagesContainer" class="messages-container">
    <div 
      v-for="(message, index) in messages" 
      :key="index"
      :class="['message', message.type === 'user' ? 'user-message' : 'assistant-message']"
    >
      <div class="message-content">
        <!-- 使用 marked 和 DOMPurify 处理消息内容 -->
        <div v-html="DOMPurify.sanitize(marked(preprocessContent(message.content)))" />
      </div>
    </div>
  </div>
</template>

<style scoped>
.messages-container {
  width: 100%; /* 和消息输入框一样宽 */
  height: calc(100vh - 72px - 80px); /* 总高度减去导航栏和输入框高度 */
  overflow-y: auto; /* 当消息过多时显示滚动条 */
  display: flex;
  flex-direction: column;
}

.message {
  display: flex;
  margin: 8px 0;
  padding: 0 16px;
}

.user-message {
  justify-content: flex-end;
}

.assistant-message {
  justify-content: flex-start;
}

.message-content {
  padding: 12px 16px;
  border-radius: 18px;
  max-width: 70%;
  word-wrap: break-word;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  transition: all 0.3s ease;
}

.user-message .message-content {
  background: linear-gradient(135deg, #4facfe 0%, #00f2fe 100%);
  color: white;
  border-top-right-radius: 4px;
}

.assistant-message .message-content {
  background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
  color: #333;
  border-top-left-radius: 4px;
}

.error-message .message-content {
  background: linear-gradient(135deg, #ff758c 0%, #ff7eb3 100%);
  color: white;
}
</style>
