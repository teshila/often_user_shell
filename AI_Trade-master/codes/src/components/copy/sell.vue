<template>
	<div class="wrap">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">买编辑</h3>
			</div>
			<div class="panel-body">
				<ul class="list-group">
					<li class="list-group-item">
						<div class="container">
							<div class="row clearfix">
								<div class="col-md-12 column">
									<form class="form-horizontal" role="form">
										<div class="form-group">
											<label for="code" class="col-sm-2 control-label">代码</label>
											<div class="col-sm-10">
												<input type="text" class="form-control" id="code" autofocus="autofocus" placeholder="请输入代码或者拼音简称" />
											</div>
										</div>

										<div class="form-group">
											<div class="col-sm-offset-2 col-sm-10">
												<a class="btn btn-default" id="doSubmints" data-toggle="tooltip" data-placement="right">保存</a>
											</div>
										</div>
									</form>
								</div>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</div>
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">当前买单列表</h3>
			</div>
			<div class="panel-body">
				<ul class="list-group" id="lists"></ul>
			</div>
		</div>
	</div>

</template>

<script>
	export default {

	}
</script>

<style>

</style>