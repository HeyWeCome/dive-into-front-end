<script>
  import HelloWorld from "./Hello.vue";

  export default {
    data() {
      return {
        counter: 0
      }
    },
    render() {
      const increment = () => this.counter++;
      const decrement = () => this.counter--;

      return (
        <div>
          <h2>当前计数：{this.counter}</h2>
          <button onClick={increment}>+1</button>
          <button onClick={decrement}>-1</button>
          <HelloWorld>
            {{default: props => <button>我是按钮</button>}}
          </HelloWorld>
        </div>
      )
    },
  }
</script>

<style scoped>

</style>