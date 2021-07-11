// pages/index/index.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
      item:0,
      tab: 0,
       // 播放列表数据
    playlist: [{
      id: 1,
      title: '做最好的我',
      singer: '庄心妍',
      src: 'http://ting6.yymp3.net:82/new27/mljyyj/1.mp3',
      coverImgUrl: './images/cover.jpg'
    }, {
      id: 2,
      title: '奏鸣曲',
      singer: '莫扎特',
      src: 'http://ting6.yymp3.net:82/new27/yuesisi/1.mp3',
      coverImgUrl: './images/cover.jpg'
    }, {
      id: 3,
      title: '欢乐颂',
      singer: '贝多芬',
      src: 'http://ting6.yymp3.net:82/new27/zhangbeibei/2.mp3',
      coverImgUrl: './images/cover.jpg'
    }, {
      id: 4,
      title: '爱之梦',
      singer: '李斯特',
      src: 'http://ting6.yymp3.net:82/new27/zhuangxinyan16/1.mp3',
      coverImgUrl: './images/cover.jpg'
    }],
    state: 'paused',
    playIndex: 0,
    play: {
      currentTime: '00:00',
      duration: '00:00',
      percent: 0,
      title: '',
      singer: '',
      coverImgUrl: './images/cover.jpg',
    }
  },
  
  
  changeItem:function(e){
     var itemSelect = e.target.dataset.item;
     console.log(itemSelect)
     this.setData({item : itemSelect});
  },

  changeTab:function(e){
      this.setData({tab:e.detail.current});
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  audioCtx: null,
  // audioCtx.src="http://ting6.yymp3.net:82/new27/huling7/1.mp3";
  onReady: function () {
    this.audioCtx = wx.createInnerAudioContext()
    // 默认选择第1曲
    this.setMusic(0)
    var that = this
    // 播放进度检测
    this.audioCtx.onError(function() {
      console.log('播放失败：' + that.audioCtx.src)
    })
    // 播放完成自动换下一曲
    this.audioCtx.onEnded(function() {
      that.next()
    })
    // 自动更新播放进度
    this.audioCtx.onPlay(function() {})
    this.audioCtx.onTimeUpdate(function() {
      that.setData({
        'play.duration': formatTime(that.audioCtx.duration),
        'play.currentTime': formatTime(that.audioCtx.currentTime),
        'play.percent': that.audioCtx.currentTime / that.audioCtx.duration * 100
      })
    })
    // 格式化时间
    function formatTime(time) {
      var minute = Math.floor(time / 60) % 60;
      var second = Math.floor(time) % 60
      return (minute < 10 ? '0' + minute : minute) + ':' + (second < 10 ? '0' + second : second)
    }
   
    //audioCtx.play();
  },
  setMusic: function(index) {
    var music = this.data.playlist[index]
    this.audioCtx.src = music.src
    this.setData({
      playIndex: index,
      'play.title': music.title,
      'play.singer': music.singer,
      'play.coverImgUrl': music.coverImgUrl,
      'play.currentTime': '00:00',
      'play.duration': '00:00',
      'play.percent': 0
    })
  },

  // 播放按钮
  play: function() {
    this.audioCtx.play()
    this.setData({
      state: 'running'
    })
  },

  // 暂停按钮
  pause: function() {
    this.audioCtx.pause()
    this.setData({
      state: 'paused'
    })
  },
  // 下一曲按钮
  next: function() {
    var index = this.data.playIndex >= this.data.playlist.length - 1 ? 0 : this.data.playIndex + 1
    this.setMusic(index)
    if (this.data.state === 'running') {
      this.play()
    }
  },
  
  // 滚动条调节歌曲进度
  sliderChange: function(e) {
    var second = e.detail.value * this.audioCtx.duration / 100
    this.audioCtx.seek(second)
  },

  // 播放列表换曲功能
  change: function(e) {
    this.setMusic(e.currentTarget.dataset.index)
    this.play()
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


  
})