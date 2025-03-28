<script setup>
import { ref } from 'vue';
import api from '../api.js';

const emit = defineEmits(['send-message']);
const inputText = ref('');
const isLoading = ref(false);

const sendMessage = async () => {
  if (!inputText.value.trim() || isLoading.value) return;
  
  isLoading.value = true;
  try {
    emit('send-message', {
      type: 'user',
      content: inputText.value
    });
    
    const response = await api.post('/generate', inputText.value, {
      headers: {
        'Content-Type': 'text/plain'
      },
      responseType: 'text'
    });
    
    if (typeof response.data !== 'string') {
      throw new Error('Invalid response format');
    }
    console.log('API Response:', response.data);
    emit('send-message', {
      type: 'assistant',
      content: response.data
    });
  } catch (error) {
    console.error('API Error:', error);
    const errorMessage = error.code === 'ECONNABORTED' 
      ? '请求超时，请检查网络连接后重试'
      : '请求失败，请稍后重试';
    emit('send-message', {
      type: 'error',
      content: errorMessage
    });
  } finally {
    inputText.value = '';
    isLoading.value = false;
  }
};
</script>

<template>
  <div class="input-container">
    <textarea
      v-model="inputText"
      @keydown.enter.experimental="sendMessage"
      placeholder="输入你的问题..."
      :disabled="isLoading"
    ></textarea>
    <button @click="sendMessage" :disabled="isLoading">
      {{ isLoading ? '发送中...' : '发送' }}
    </button>
  </div>
</template>

<style scoped>
.input-container {
  position: relative;
  display: flex;
  flex-direction: column;
  margin: 10px;
}

textarea {
  width: 100%;
  padding: 12px;
  box-sizing: border-box;
  border: 1px solid #ccc;
  border-radius: 8px;
  font-size: 16px;
  outline: none;
  transition: border-color 0.3s ease;
}

textarea:focus {
  border-color: #007BFF;
  box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
}

button {
  position: absolute;
  bottom: 12px;
  right: 12px;
  padding: 8px 16px;
  background-color: #007BFF;
  color: white;
  border: none;
  border-radius: 6px;
  font-size: 14px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

button:hover {
  background-color: #0056b3;
}

button:disabled {
  background-color: #ccc;
  cursor: not-allowed;
}
</style>