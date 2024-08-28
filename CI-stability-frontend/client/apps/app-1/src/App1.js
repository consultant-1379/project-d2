/**
 * App1 is defined as
 * `<e-app-1>`
 *
 * Imperatively create application
 * @example
 * let app = new App1();
 *
 * Declaratively create application
 * @example
 * <e-app-1></e-app-1>
 *
 * @extends {App}
 */
import { definition } from '@eui/component';
import { App, html } from '@eui/app';
import style from './app1.css';

@definition('e-app-1', {
  style,
  props: {
    data1: { type: Object },
    data2: { type: Object },
  },
})
export default class App1 extends App {
  /**
   * Uncomment this block to add initialization code
   * constructor() {
   *   super();
   *   // initialize
   * }
   */

  didConnect() {
    fetch('/data')
    .then((response) => {
      return response.json();
    }).then((json) => {
      this.data1 = json.data1;
      this.data2 = json.data2;

    }).catch((error) => {
      console.log('parsing failed', error);
    });
  }

  /**
  * Render the <e-app-1> app. This function is called each time a
  * prop changes.
  */
  render() {
    return html`
     <e-my-component></e-my-component>
     <div class='grid'>
       ${this.data1 && html`
         <eui-layout-v0-tile tile-title='CI Stability Result 1' subtitle=''>
           <e-line-chart
               live-data1
               slot='content'
               .data=${this.data1}
               .config=${{
                 x: { unit: 'Date' },
                 y: { unit: 'Build Failure Rate'}
               }}
           ></e-line-chart>
         </eui-layout-v0-tile>`}
 
       ${this.data2 && html`
       <eui-layout-v0-tile tile-title='CI Stability Result 2' subtitle=''>
         <e-line-chart
             live-data2
             slot='content'
             .data=${this.data2}
             .config=${{
               x: { unit: 'Date' },
               y: { unit: 'Build Failure Recovery Time (Days)'}
             }}
         ></e-line-chart>
       </eui-layout-v0-tile>`}
     </div>
    `;
  }
}

/**
 * Register the component as e-app-1.
 * Registration can be done at a later time and with a different name
 * Uncomment the below line to register the App if used outside the container
 */
// App1.register();
