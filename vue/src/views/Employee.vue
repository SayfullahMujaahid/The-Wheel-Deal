<template>
<div>
    <Header />
   <employee-dashboard />
   </div>
</template>

<script>
import EmployeeDashboard from '../components/EmployeeDashboard'
import Header from '../components/Header'
export default {
components: {
    EmployeeDashboard,
    Header
}
};
</script>

<style >

</style>