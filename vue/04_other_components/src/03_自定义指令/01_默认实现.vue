<template>
  <div>
    <input type="text" ref="input">
  </div>
</template>

<script>
  import { ref, onMounted } from 'vue';

  export default {
    setup () {
      const input = ref(null);

      onMounted(() => {
        // 自动获取焦点
        input.value.focus();
      });
      return {
        input
      }
    }
  }
</script>

<style lang="scss" scoped>

</style>