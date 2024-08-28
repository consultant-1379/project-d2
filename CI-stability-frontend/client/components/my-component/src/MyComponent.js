/**
 * Component MyComponent is defined as
 * `<e-my-component>`
 *
 * Imperatively create component
 * @example
 * let component = new MyComponent();
 *
 * Declaratively create component
 * @example
 * <e-my-component></e-my-component>
 *
 * @extends {LitComponent}
 */
 import { definition } from '@eui/component';
 import { LitComponent, html } from '@eui/lit-component';
 import style from './myComponent.css';
 import '@eui/layout';
 import '@eui/table';
 
 
 global.receivingData;
 
 const columns = [
   { title: 'Metric', attribute: 'col1' },
   { title: 'Result', attribute: 'col2' }
 ];
 const data1 = [
   { col1: 'Build failure rate', col2: 'High'},
   { col1: 'Average build failure recovery time', col2: 'High'},
   { col1: 'Std of build failure recovery time', col2: 'High'},
 ];
 const data2 = [
   { col1: 'Build failure rate', col2: 'Low'},
   { col1: 'Average build failure recovery time', col2: 'Low'},
   { col1: 'Std of build failure recovery time', col2: 'Low'},
 ];
 
 let SERVER_URL = "http://localhost:9090/";
 /**
  * @property {Boolean} propOne - show active/inactive state.
  * @property {String} propTwo - shows the "Hello World" string.
  */
 @definition('e-my-component', {
   style,
   home: 'my-component',
   props: {
     startDateInput: { attribute: false, type: Boolean, default: false },
     endDateInput: { attribute: false, type: Boolean, default: false },
     startDate: {attribute: true, type: String},
     endDate: {attribute: true, type: String},
     jobs: {attribute: true, type: Array},
     check_1: { attribute: true, type: Boolean, default: false },
     check_2: { attribute: true, type: Boolean, default: false },
     check_3: { attribute: true, type: Boolean, default: false },
     check_4: { attribute: true, type: Boolean, default: false },
     check_5: { attribute: true, type: Boolean, default: false },
     check_6: { attribute: true, type: Boolean, default: false },
     check_7: { attribute: true, type: Boolean, default: false },
     check_8: { attribute: true, type: Boolean, default: false },
     check_9: { attribute: true, type: Boolean, default: false },
     check_10: { attribute: true, type: Boolean, default: false },
     check_11: { attribute: true, type: Boolean, default: false },
     check_12: { attribute: true, type: Boolean, default: false },
     markerData1: { type: Array },
   },
 })
 export default class MyComponent extends LitComponent {
   /**
    * Render the <e-my-component> component. This function is called each time a
    * prop changes.
    */
   render() {
     
     return html`<h1 class="title_main">CI Stability Dashboard</h1>
     <p>
     <div class="layout__dashboard" @eui-tile:maximize=${this} @eui-tile:minimize=${this}>
       <eui-layout-v0-tile class="tileTitle1" tile-title="All available jobs" subtitle="" column=0 column-span=1 maximizable>
         <div slot="content">
           <eui-base-v0-checkbox name="checkbox-1" ?checked="${this.check_1}" @change="${(event) => this._function_to_checkbox_1()}">
           eric-oss-ran-topologyadapter_Publish
           </eui-base-v0-checkbox>
 
           <eui-base-v0-checkbox name="checkbox-2" ?checked="${this.check_2}" @change="${(event) => this._function_to_checkbox_2()}">
           eric-oss-ran-topologyadapter_PreCodeReview
           </eui-base-v0-checkbox>
 
           <eui-base-v0-checkbox name="checkbox-3" ?checked="${this.check_3}" @change="${(event) => this._function_to_checkbox_3()}">
           ENM-Adapter_release
           </eui-base-v0-checkbox>
 
           <eui-base-v0-checkbox name="checkbox-4" ?checked="${this.check_4}" @change="${(event) => this._function_to_checkbox_4()}">
           ENM-Adapter_PreCodeReview
           </eui-base-v0-checkbox>
 
           <eui-base-v0-checkbox name="checkbox-5" ?checked="${this.check_5}" @change="${(event) => this._function_to_checkbox_5()}">
           ENM-Stub_release
           </eui-base-v0-checkbox>
 
           <eui-base-v0-checkbox name="checkbox-6" ?checked="${this.check_6}" @change="${(event) => this._function_to_checkbox_6()}">
           ENM-Stub_PreCodeReview
           </eui-base-v0-checkbox>
 
           <eui-base-v0-checkbox name="checkbox-7" ?checked="${this.check_7}" @change="${(event) => this._function_to_checkbox_7()}">
           eric-oss-enm-discoveryadapter_Publish
           </eui-base-v0-checkbox>
 
           <eui-base-v0-checkbox name="checkbox-8" ?checked="${this.check_8}" @change="${(event) => this._function_to_checkbox_8()}">
           eric-oss-enm-discoveryadapter_PreCodeReview
           </eui-base-v0-checkbox>
 
           <eui-base-v0-checkbox name="checkbox-9" ?checked="${this.check_9}" @change="${(event) => this._function_to_checkbox_9()}">
           eric-oss-enm-modeladapter_Publish
           </eui-base-v0-checkbox>
 
           <eui-base-v0-checkbox name="checkbox-10" ?checked="${this.check_10}" @change="${(event) => this._function_to_checkbox_10()}">
           eric-oss-enm-modeladapter_PreCodeReview
           </eui-base-v0-checkbox>
 
           <eui-base-v0-checkbox name="checkbox-11" ?checked="${this.check_11}" @change="${(event) => this._function_to_checkbox_11()}">
           eric-oss-enm-notificationadapter_Publish
           </eui-base-v0-checkbox>
 
           <eui-base-v0-checkbox name="checkbox-12" ?checked="${this.check_12}" @change="${(event) => this._function_to_checkbox_12()}">
           eric-oss-enm-notificationadapter_PreCodeReview
           </eui-base-v0-checkbox>
           <p>
 
           <div class="date_range">    
             <div class="datepicker">
             <p1> Start Date: </p1>
             <input type="text" placeholder="YYYY-MM-DD" name="datepicker" maxlength="10" class="with-icon"
               min="2020-10-05" max="2029-10-15" @input="${event => this._onChangeHandlerStartDate(event.currentTarget.value)}">
             </div>
             <p>
             <div class="datepicker">
             <p1> End Date: </p1>
             <input type="text" placeholder="YYYY-MM-DD" name="datepicker" maxlength="10" class="with-icon"
               min="2020-10-05" max="2029-10-15" @input="${event => this._onChangeHandlerEndDate(event.currentTarget.value)}">
             </div>
           </div>
           <p>

           <eui-base-v0-button ?disabled="${!(this.startDateInput && this.endDateInput && (this.check_1 || this.check_2 || this.check_3 || this.check_4 || 
             this.check_5 || this.check_6 || this.check_7 || this.check_8 || this.check_9 || this.check_10 || this.check_11 || this.check_12))}" 
             @click="${() => this._function_post_selected_jobs()}" primary>
             Search
           </eui-base-v0-button>
           <eui-base-v0-button ?disabled="${!(this.startDateInput && this.endDateInput)}" 
             @click="${() => this._function_reset_selected_jobs()}" primary>
             Reset
           </eui-base-v0-button>
           
         </div>
       </eui-layout-v0-tile>
       <eui-layout-v0-tile class="tileTitle2" tile-title="Specifications" subtitle="Unstable Builds" column=0>
         <div slot="content">
           <eui-table-v0 .columns=${columns} .data=${data1}></eui-table-v0>
         </div>
       </eui-layout-v0-tile>
       <eui-layout-v0-tile class="tileTitle3" tile-title="Specifications" subtitle="Stable Builds" column=0>
         <div slot="content">
           <eui-table-v0 .columns=${columns} .data=${data2}></eui-table-v0>
         </div>
       </eui-layout-v0-tile>
       
     </div>
     `;
   }
 
   _function_to_checkbox_1() {
     this.check_1 = !this.check_1;
     // console.log(this.check_1);
   }
 
   _function_to_checkbox_2() {
     this.check_2 = !this.check_2;
     // console.log(this.check_2);
   }
 
   _function_to_checkbox_3() {
     this.check_3 = !this.check_3;
     // console.log(this.check_3);
   }
 
   _function_to_checkbox_4() {
     this.check_4 = !this.check_4;
     // console.log(this.check_4);
   }
 
   _function_to_checkbox_5() {
     this.check_5 = !this.check_5;
     // console.log(this.check_5);
   }
 
   _function_to_checkbox_6() {
     this.check_6 = !this.check_6;
     // console.log(this.check_6);
   }
 
   _function_to_checkbox_7() {
     this.check_7 = !this.check_7;
     // console.log(this.check_7);
   }
 
   _function_to_checkbox_8() {
     this.check_8 = !this.check_8;
     // console.log(this.check_8);
   }
 
   _function_to_checkbox_9() {
     this.check_9 = !this.check_9;
     // console.log(this.check_9);
   }
 
   _function_to_checkbox_10() {
     this.check_10 = !this.check_10;
     // console.log(this.check_10);
   }
 
   _function_to_checkbox_11() {
     this.check_11 = !this.check_11;
     // console.log(this.check_11);
   }
 
   _function_to_checkbox_12() {
     this.check_12 = !this.check_12;
     // console.log(this.check_12);
   }
 
   _onChangeHandlerStartDate(value) {
  
     if (value === '') {
       this.startDateInput = false;
     } else {
       this.startDate = value;
       this.startDateInput = true;
     }
   }
 
   _onChangeHandlerEndDate(value) {
  
     if (value === '') {
       this.endDateInput = false;
     } else {
       this.endDate = value;
       this.endDateInput = true;
     }
   }
 
   _function_reset_selected_jobs() {
     window .location.reload();
   }
 
   _function_post_selected_jobs() {
     const jobs = [
       "eric-oss-ran-topology-adapter_Publish",
       "eric-oss-ran-topology-adapter_PreCodeReview",
       "ENM-Adapter_release",
       "ENM-Adapter_PreCodeReview",
       "ENM-Stub_release",
       "ENM-Stub_PreCodeReview",
       "eric-oss-enm-discovery-adapter_Publish",
       "eric-oss-enm-discovery-adapter_PreCodeReview",
       "eric-oss-enm-model-adapter_Publish",
       "eric-oss-enm-model-adapter_PreCodeReview",
       "eric-oss-enm-notification-adapter_Publish",
       "eric-oss-enm-notification-adapter_PreCodeReview"
     ]
 
     const selectedJobs = [];
     if (this.check_1) {
       selectedJobs.push(jobs[0]);
     }
     if (this.check_2) {
       selectedJobs.push(jobs[1]);
     }
     if (this.check_3) {
       selectedJobs.push(jobs[2]);
     }
     if (this.check_4) {
       selectedJobs.push(jobs[3]);
     }
     if (this.check_5) {
       selectedJobs.push(jobs[4]);
     }
     if (this.check_6) {
       selectedJobs.push(jobs[5]);
     }
     if (this.check_7) {
       selectedJobs.push(jobs[6]);
     }
     if (this.check_8) {
       selectedJobs.push(jobs[7]);
     }
     if (this.check_9) {
       selectedJobs.push(jobs[8]);
     }
     if (this.check_10) {
       selectedJobs.push(jobs[9]);
     }
     if (this.check_11) {
       selectedJobs.push(jobs[10]);
     }
     if (this.check_12) {
       selectedJobs.push(jobs[11]);
     }
     const send_data = {
       
       "startDate": this.startDate,
       "endDate": this.endDate,
       "jobs": selectedJobs
       };
     
     fetch('http://localhost:9090/request/getData', {
       method: 'POST', // or 'PUT'
       headers: {
         'Content-Type': 'application/json',
       },
       body: JSON.stringify(send_data),
     })
     .then(response => response.json())
     .then(data => {
       console.log('Success:', data);
       global.receivingData = data;
       console.log('Global data: ', global.receivingData);
       this.didUpgrade(data);
     })
     .catch((error) => {
       console.error('Error:', error);
     });
     
   }
 
   handleEvent(event) {
     if (event.type === 'eui-tile:maximize') {
       const tiles = this.shadowRoot.querySelectorAll('eui-layout-v0-tile');
       for (let i = 0; i < tiles.length; i += 1) {
         tiles[i].hidden = !tiles[i].maximize;
       }
 
       this.shadowRoot.querySelector('.layout__dashboard').classList.add('layout__dashboard--fullscreen');
     }
 
     if (event.type === 'eui-tile:minimize') {
       const tiles = this.shadowRoot.querySelectorAll('eui-layout-v0-tile');
       for (let i = 0; i < tiles.length; i += 1) {
         tiles[i].hidden = false;
       }
 
     this.shadowRoot.querySelector('.layout__dashboard').classList.remove('layout__dashboard--fullscreen');
     }
   }
   
 }
 
 /**
  * Register the component as e-my-component.
  * Registration can be done at a later time and with a different name
  */
 MyComponent.register();
 
