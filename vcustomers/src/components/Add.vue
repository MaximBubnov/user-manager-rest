<template>
  <div class="add container">
    <Alert v-if="alert" v-bind:message="alert"/>
    <h1 class="page-header">Add Customer</h1>
    <form v-on:submit="addCustomer">

      <div class="well">
        <h4>Customer Info</h4>
        <div class="form-group">
          <label>First Name</label>
          <input type="text" class="form-control" placeholder="First Name" v-model="customer.firstname">
        </div>
        <div class="form-group">
          <label>Last Name</label>
          <input type="text" class="form-control" placeholder="Last Name" v-model="customer.lastname">
        </div>
        <div class="form-group">
          <label>Age</label>
          <input type="text" class="form-control" placeholder="Age" v-model="customer.age">
        </div>
      </div>
      <div class="well">
        <h4>Customer Contact</h4>
        <div class="form-group">
          <label>Email</label>
          <input type="text" class="form-control" placeholder="Email" v-model="customer.email">
        </div>
        <div class="form-group">
          <label>Phone</label>
          <input type="text" class="form-control" placeholder="Phone" v-model="customer.phone">
        </div>
      </div>

      <div class="well">
        <h4>Customer Location</h4>
        <div class="form-group">
          <label>Address</label>
          <input type="text" class="form-control" placeholder="Address" v-model="customer.address">
        </div>
        <div class="form-group">
          <label>City</label>
          <input type="text" class="form-control" placeholder="City" v-model="customer.city">
        </div>
        <div class="form-group">
          <label>State</label>
          <input type="text" class="form-control" placeholder="State" v-model="customer.state">
        </div>
      </div>
      <button type="submit" class="btn btn-primary">Submit</button>

    </form>
  </div>
</template>

<script>
  import Alert from "./Alert"
  export default {
    name: 'add',
    components: {
      Alert
    },
    data () {
      return {
        customer: {},
        alert: ''
      }
    },
    methods: {
      addCustomer(e){
        if(!this.customer.firstname || !this.customer.lastname || !this.customer.age || !this.customer.email) {
         this.alert = 'Please fill the filed';
        } else {

          let newCustomer = {
            firstname: this.customer.firstname,
            lastname: this.customer.lastname,
            age: this.customer.age,
            email: this.customer.email,
            phone: this.customer.phone,
            address: this.customer.address,
            city: this.customer.city,
            state: this.customer.state,
          };

          this.$http.post('http://localhost:8080/manage', newCustomer)
            .then(result => result.json().then(data => {
              this.$router.push({path: '/', query: {alert: 'Customer successfully Added'}});
            }));

          e.preventDefault();

        }
        e.preventDefault();
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
