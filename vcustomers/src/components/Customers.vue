<template>
  <div class="customers container">
    <Alert v-if="alert" v-bind:message="alert"/>
    <h1 class="page-header">Manage Customers</h1>
    <input type="text" class="form-control" placeholder="FILTER : Enter Last name" v-model="filterInput">
    <br>
    <table class="table table-striped">
      <thead>
        <tr>
          <th>Last Name</th>
          <th>First name</th>
          <th>Age</th>
          <th></th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="customer in filterBy(sortedCustomers, filterInput)">
          <td>{{customer.lastname}}</td>
          <td>{{customer.firstname}}</td>
          <td>{{customer.age}}</td>
          <td><router-link class="btn btn-default" v-bind:to="'/customer/' + customer.id">View</router-link></td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
  import Alert from './Alert'
export default {
  name: 'customers',
  components: {
    Alert
  },
  data () {
    return {
      customers:[],
      alert: '',
      filterInput: ''
    }
  },
  methods: {
    fetchCustomers(){
      this.$http.get('http://localhost:8080/manage/customers')
        .then(result => result.json().then(data =>
          data.forEach(customer => this.customers.push(customer))));
    },
    filterBy(list, value) {
      return list.filter(function (customer) {
        return customer.lastname.indexOf(value) > -1;
      })
    }
  },
  created: function () {
    if(this.$route.query.alert) {
      this.alert = this.$route.query.alert;
    }
    this.fetchCustomers();
  },
  computed: {
    sortedCustomers(){
      return this.customers.sort((a,b) => -(a.id - b.id));
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
