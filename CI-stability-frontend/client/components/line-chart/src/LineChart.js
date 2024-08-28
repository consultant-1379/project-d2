/**
 * Component LineChart is defined as
 * `<e-line-chart>`
 *
 * Imperatively create component
 * @example
 * let component = new LineChart();
 *
 * Declaratively create component
 * @example
 * <e-line-chart></e-line-chart>
 *
 * @extends {LitComponent}
 */
 import { definition } from '@eui/component';
 import { LitComponent, html } from '@eui/lit-component';
 import { LineChart as EDSLineChart } from '@eds/vanilla/charts/line-chart/LineChart';
 import style from './lineChart.css';
 import '../../chart-legend/src/ChartLegend';
 
 
 /**
  * @property {Boolean} propOne - show active/inactive state.
  * @property {String} propTwo - shows the "Hello World" string.
  */
 @definition('e-line-chart', {
   style,
   home: 'line-chart',
   props: {
     data: { type: Object },
     config: { type: Object },
     markerData: { type: Array },
     hideLegend: { attribute: true, type: Boolean },
     liveData1: { attribute: true, type: Boolean },
     liveData2: { attribute: true, type: Boolean },
   },
 })
 export default class LineChart extends LitComponent {
   /**
    * Render the <e-line-chart> component. This function is called each time a
    * prop changes.
    */
    
    didUpgrade() {
     // observe changes in the e-histogram size.
     this.resizeObserver = new ResizeObserver(() => {
       this.chart.draw();
     });
 
     this.chart = new EDSLineChart({
       element: this.shadowRoot.getElementById('eds-line-chart'),
       data: this.data,
       ...this.config,
       
     });
     this.chart.init();
 
     // set the width of svg to 100% to fix resize issue...
     this.shadowRoot.querySelector('.line-chart').setAttribute('width', '100%');
 
     // destroy EDS onResize listened as we are using our own.
     this.chart.stage.destroy();
 
     // observe changes to the size of e-histogram-chart
     this.resizeObserver.observe(this);
 
     if (this.liveData1) {
       // generate new values every 3 seconds to show live updates
       const timer = setInterval(() => {
         if (global.receivingData != null) {
           this.data.series[0].values = global.receivingData.failureRates;
           this.data.common = global.receivingData.dates;
         }
         
         this.data = { ...this.data };
         this.chart.draw(this.data);
       }, 1000);
     }
 
     if (this.liveData2) {
       // generate new values every 3 seconds to show live updates
       const timer = setInterval(() => {
         if (global.receivingData != null) {
           this.data.series[0].values = global.receivingData.medians;
           this.data.series[1].values = global.receivingData.stds;
           this.data.common = global.receivingData.dates;
         }
         
         this.data = { ...this.data };
         this.chart.draw(this.data);
       }, 1000);
     }
   }
 
   didConnect() {
     if (this.resizeObserver) {
       // observe changes to the size of e-histogram-chart
       this.resizeObserver.observe(this);
     }
   }
 
   didDisconnect() {
     if (this.resizeObserver) {
       // unobserve changes to the size of e-histogram-chart
       this.resizeObserver.unobserve(this);
     }
   }
 
   didChangeProps(changedProps) {
     // redraw the chart when the data changes...
     if (changedProps.has('data')) {
       this.chart.draw(this.data);
     }
   }
 
   /**
    * @function highlight
    * @param {Chart} chart - EDS Chart instance
    * @param {String} name - name of the series to highlight in the chart
    */
   highlight(chart, name) {
     if (typeof chart._highlight === 'function') {
       chart._highlight(name);
     }
   }
 
   handleEvent(event) {
     if (event.type === 'legend:filter') {
       this.chart.draw(event.target.filter());
       this.highlight(this.chart);
     }
     if (event.type === 'legend:highlight') {
       this.highlight(this.chart, event.detail);
     }
     if (event.type === 'legend:highlight-off') {
       this.highlight(this.chart);
     }
   }
 
   /**
    * Render the <e-histogram-chart> component. This function is called each time a
    * prop changes.
    */
   render() {
     return html`
     <e-chart-legend
       @legend:filter=${this}
       @legend:highlight=${this}
       @legend:highlight-off=${this}
       ?hidden=${this.hideLegend}
       .data=${this.data}
     ></e-chart-legend>
     <div class='eds-line-chart' id='eds-line-chart'></div>
     `;
   }
 }
 
 /**
  * Register the component as e-line-chart.
  * Registration can be done at a later time and with a different name
  */
 LineChart.register();
 