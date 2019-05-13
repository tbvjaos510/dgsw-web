import React, {Component} from 'react';

class Operator extends Component {

    clickHandler = (event) => {
        this.props.clickHandler(event.target.innerText);
    };

    render() {
        return (
            <div>
                <button onClick={this.clickHandler}>+</button>
                <button onClick={this.clickHandler}>-</button>
                <button onClick={this.clickHandler}>*</button>
                <button onClick={this.clickHandler}>/</button>
            </div>
        );
    }
}

export default Operator;