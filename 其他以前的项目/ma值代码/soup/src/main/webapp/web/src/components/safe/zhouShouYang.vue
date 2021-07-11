<template>
	<div>
		<chose url='zhouShouYangList.do' searchURL="findZhouShouYang"  getTimeURL="getZhouShouYangTime"  title="短线(周连阳)"></chose>

	</div>
</template>

<script>
	import chose from './chose';

	export default {
		components: {
			chose
		}

	}
</script>

<style>
</style>



