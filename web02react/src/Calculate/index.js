import React, {Component} from 'react';

import Operator from "./operator"
class Counter extends Component {

    state = {
        num1: 0,
        num2: 0,
        result: 0
    };

    calculate = (oper) => {
        this.setState({
            result: eval(this.state.num1 + oper + this.state.num2)
        });
    };

    changeHandler = (event, variable) => {
      this.setState({
          [variable]: event.target.value
      });
    };

    render() {
        const { result, num1, num2 } = this.state;
        return (
            <div>
                <input type="text" value={num1} onChange={(e) => this.changeHandler(e, "num1")}/>
                <input type="text" value={num2} onChange={(e) => this.changeHandler(e, "num2")}/>
                <Operator clickHandler={this.calculate} />
                <p>{result}</p>
            </div>
        );
    }
}

export default Counter;