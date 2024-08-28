const externals = {
  apps: [{
    path: "app-1",
    entry: "App1"
  }],
  components: {
    default: [],
    shareable: [{
      path: "chart-legend",
      entry: "ChartLegend"
    }, {
      path: "line-chart",
      entry: "LineChart"
    }, {
      path: "my-component",
      entry: "MyComponent"
    }]
  },
  panels: [],
  plugins: []
};
module.exports = externals;