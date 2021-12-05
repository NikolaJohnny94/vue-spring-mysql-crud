module.exports = {
  chainWebpack: (config) => {
    config.plugin('html').tap((args) => {
      args[0].title =
        'Full Stack VSJM ⛰️🍃☕🐬 CRUD APP plus Bootstrap 🥾 | VSJM'
      args[0].meta = {
        description:
          'Full Stack [V]ue.js ⛰️ [S]pring Boot 🍃 [J]ava ☕ [M]ySQL 🐬 CRUD web app + Bootstrap 🥾',
      }
      return args
    })
  },
}
