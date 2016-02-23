
# Vaas
### Things Everyone Cares About:
#### Current Ports: 
Thing | Competition Bot | Practice Bot
--------|-----------------------|------------------
left side talons (looking from the intake) | `5 & 3` | `5 & 3`
right side talons (looking from the intake) |  `1 & 4` | `1 & 6`
intake spin talon | `6` | `4`
intake arm talon | `2` | `2`
intake encoder | `0,1` | `4,5`
intake button | `2` | `6`
right drivetrain encoder | `3,4` | `0,1`
left drivetrain encoder | `5,6` | `2,3`
PCM | `20` | `20`

### Control Scheme:
#### Controller -
- Arcade Drive 
  - left stick controls forward and backwards, right stick controls left and right

#### Joystick -
- Defaults to Vertical Position
- Intake: `7` 
  - will stop intaking while button is pressed
- Outwards Outtake: `6`
- Ground Position: `8`
- Vertical Position: `9`
- Intake Into Catapult: `2`
- Shoot: `trigger`
- Zero the Intake: `11`

![squidward future](https://s-media-cache-ak0.pinimg.com/236x/bc/f8/97/bcf89787b054ba512c777abfad16fff4.jpg)
- [ ] tune intake PID if necessary
- [x] make catapult not be the t-shirt cannon
- [ ] update sensor base as necessary
- [ ] voltage sensing for intaking balls to replace intake button
- [ ] timeout command
- [ ] commands for crossing defenses in autonomous
- [ ] vision
- [ ] fine tune turn mode for shooting
