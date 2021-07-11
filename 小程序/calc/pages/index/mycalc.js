// pages/index/mycalc.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
      num1:"",
      num2:"",
      result:""
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
     
  },


  formCompare:function (e) {
      var str ="两个数相等";
      var num1 = Number(e.detail.value.num1);
      var num2 = Number(e.detail.value.num2);
      if(num1>num2){
        str ="第一个数大";
      }else if(num1<num2){
        str ="第二个数大";
      }
      this.setData({result:str})

  }



})