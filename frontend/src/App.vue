<script>
import axios from 'axios'
import {v4 as uuidv4} from 'uuid'
export default {
    data() {
        return {
            message: '',
            error: '',
            sendingResult: '',
            readingResult: [],
            dosAttackIsRun: false,
            dosAttackTimer: undefined
        }
    },
    methods: {
        sendMessage() {
            if (this.message.length === '') {
                return false;
            }

            this.error = ''
            this.sendingResult = ''
            axios.get(`http://localhost:8081?msg=${this.message}`)
                .then(res => this.sendingResult = res.data)
                .catch(res => this.error = res.data)
        },

        readMessage() {
            axios.get(`http://localhost:8082/get-all`)
                .then(res => {
                    this.readingResult = res.data
                })
        },

        startDosAttack() {
            this.dosAttackIsRun = true;
            this.dosAttackTimer = setInterval(() => {
                axios.get(`http://localhost:8081?msg=${uuidv4()}`)
            }, 10);
        },

        stopDosAttack() {
            this.dosAttackIsRun = false;
            clearInterval(this.dosAttackTimer)
        }
    }
}
</script>

<template>
  <div class="wrapper">
      <h1>Отправка сообщения</h1>
      <p>Отправить сообщение консьюмеру</p>
      <input type="text" v-model="message" placeholder="Введите сообщение">
      <button v-if="message !== ''" @click="sendMessage()">Отправить</button>
      <button disabled v-else @click="sendMessage()">Отправить</button>

      <button class="dos-stop" v-if="this.dosAttackIsRun" @click="stopDosAttack()">Остановить DoS-атаку</button>
      <button class="dos" v-else @click="startDosAttack()">DoS-атака</button>

      <p class="error" v-if="error !== ''">{{ error }}</p>
      <p class="info" v-if="sendingResult !== ''">{{ sendingResult }}</p>
  </div>

  <div class="wrapper">
      <h1>Список сообщений</h1>
      <button @click="readMessage()">Обновить</button>
      <div class="tableFixHead">
          <table>
              <thead>
                  <tr>
                      <th>Дата и время создания соосбшения</th>
                      <th>Дата и время обработки</th>
                      <th>Сообщение</th>
                  </tr>
              </thead>
              <tbody>
                  <tr v-for="messageObj in this.readingResult">
                      <td>{{new Date(messageObj.createdAt).toLocaleString()}}</td>
                      <td>{{new Date(messageObj.readAt).toLocaleString()}}</td>
                      <td>{{messageObj.data}}</td>
                  </tr>
              </tbody>
          </table>
      </div>
  </div>
</template>

<style scoped>
  .wrapper {
      width: 900px;
      height: 500px;
      border-radius: 50px;
      margin-top: 50px;
      padding: 20px;
      background: #1f0124;
      text-align: center;
      color: white;
  }
  .wrapper h1 {
      margin-top: 50px;
  }
  .wrapper p {
      margin-top: 20px;
  }
  .wrapper input {
      margin-top: 30px;
      background: transparent;
      border-top: 0;
      border-bottom: 2px solid #110813;
      color: #fcfcfc;
      font-size: 14px;
      padding: 5px 8px;
      outline: none;
  }
  .wrapper input:focus {
      border-bottom-color: #6e2d7d;
  }
  .wrapper button {
      background: #e3bc4b;
      color: #fff;
      border-radius: 10px;
      border: 2px solid #b99935;
      padding: 10px 15px;
      margin-left: 20px;
      cursor: pointer;
      transition: transform 500ms ease;
  }

  .wrapper button.dos {
      background: #ff0000;
      color: #000000;
      border: 2px solid #d01818;
  }

  .wrapper button.dos-stop {
      background: #55d018;
      color: #000000;
      border: 2px solid #3ab429;
  }

  .wrapper button:disabled {
      background: #5e522f;
  }

  .wrapper button:hover {
      transform: scale(1.1) translateY(-5px);
  }

  .error {
      color: red;
  }

  .info {
      color: #00d000;
  }

  .wrapper table {
      margin-left: auto;
      margin-right: auto;
      text-align: left;
      border-collapse: separate;
      border-spacing: 5px;
      background: #792485;
      color: #f1f1f1;
      margin-top: 30px;
  }
  .wrapper th {
      padding: 5px;
  }
  .wrapper td {
      background: #853596;
      padding: 10px;
  }

  /*.wrapper thead, tbody { display: block; }*/

  .tableFixHead {
      overflow-y: auto; /* make the table scrollable if height is more than 200 px  */
      height: 200px; /* gives an initial height of 200px to the table */
  }

  .tableFixHead thead th {
      background: #853596;
      position: sticky; /* make the table heads sticky */
      top: 0px; /* table head will be placed from the top of the table and sticks to it */
  }
  table {
      border-collapse: collapse; /* make the table borders collapse to each other */
      width: 100%;
  }
</style>
