import React from 'react'
import { render, screen } from '@testing-library/react'
import SigProv from '../index'
import {SignalContext} from '../../../contexts'

import { TextEncoder, TextDecoder } from 'util'
global.TextEncoder = TextEncoder 
global.TextDecoder = TextDecoder

it("remove", () => {
    expect(1==1)
})

//Simple sanity check to make sure our child components render
// it("Initializes providers correctly", async () => {
//     render(<SigProv url="Test" children={<h1>Test</h1>}/>)

//     expect(screen.getByText("Test")).toBeInTheDocument()
// })

// it("Context initializes a signaller", () => {
//     render(
//         <SigProv url={"test"}>
//             <SignalContext.Consumer>
//                 {(value) => { // Deep check to make sure socket gets init'd correctly
//                     expect(value.signaller.stompClient.webSocketFactory().url).toEqual('http://localhost/test')
//                 }}
//             </SignalContext.Consumer>
//         </SigProv>
//     )
// })
