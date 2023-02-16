/* eslint-disable react/destructuring-assignment */
import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import './ToolbarComponent.css';

import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';

import Mic from '@mui/icons-material/Mic';
import MicOff from '@mui/icons-material/MicOff';
import Videocam from '@mui/icons-material/Videocam';
import VideocamOff from '@mui/icons-material/VideocamOff';
import Fullscreen from '@mui/icons-material/Fullscreen';
import FullscreenExit from '@mui/icons-material/FullscreenExit';
import SwitchVideoIcon from '@mui/icons-material/SwitchVideo';
import PictureInPicture from '@mui/icons-material/PictureInPicture';
import ScreenShare from '@mui/icons-material/ScreenShare';
import StopScreenShare from '@mui/icons-material/StopScreenShare';
import Tooltip from '@mui/material/Tooltip';
import PowerSettingsNew from '@mui/icons-material/PowerSettingsNew';
import QuestionAnswer from '@mui/icons-material/QuestionAnswer';

import IconButton from '@mui/material/IconButton';
import store from 'redux/store';
import swal from 'sweetalert2';

export default class ToolbarComponent extends Component {
  constructor(props) {
    super(props);
    this.state = { fullscreen: false };
    this.camStatusChanged = this.camStatusChanged.bind(this);
    this.micStatusChanged = this.micStatusChanged.bind(this);
    this.screenShare = this.screenShare.bind(this);
    this.stopScreenShare = this.stopScreenShare.bind(this);
    this.toggleFullscreen = this.toggleFullscreen.bind(this);
    this.switchCamera = this.switchCamera.bind(this);
    this.leaveSession = this.leaveSession.bind(this);
    this.camStatusChanged = this.camStatusChanged.bind(this);
    this.toggleChat = this.toggleChat.bind(this);
  }

  micStatusChanged() {
    this.props.micStatusChanged();
  }

  camStatusChanged() {
    this.props.camStatusChanged();
  }

  screenShare() {
    this.props.screenShare();
  }

  stopScreenShare() {
    this.props.stopScreenShare();
  }

  toggleFullscreen() {
    this.setState({ fullscreen: !this.state.fullscreen });
    this.props.toggleFullscreen();
  }

  switchCamera() {
    this.props.switchCamera();
  }

  leaveSession() {
    swal.fire('상담룸에서 나가시겠습니까?', '', 'info').then(e => {
      if (e.isConfirmed()) {
        this.props.leaveSession();
        if (this.props.isShelter) {
          window.location.href = `/shelter/${String(store.getState().shelter.value.resShelterId)}/consulting`;
        } else {
          window.location.href = '/mypage/consulting';
        }
      }
    });
  }
  camStatusChanged() {
    this.props.camStatusChanged();
  }
  toggleChat() {
    this.props.toggleChat();
  }

  render() {
    // const mySessionId = this.props.sessionId;
    const localUser = this.props.user;
    const isShelter = this.props.isShelter;
    return (
      <AppBar className="toolbar" id="header">
        <Toolbar className="toolbar">
          <div className="buttonsContent">
            <IconButton
              sx={{ color: '#f39c12', size: 50 }}
              className="navButton"
              id="navMicButton"
              title="음소거"
              onClick={this.micStatusChanged}
            >
              {localUser !== undefined && localUser.isAudioActive() ? <Mic /> : <MicOff sx={{ color: '#dc0000' }} />}
            </IconButton>

            <IconButton
              sx={{ color: '#f39c12' }}
              className="navButton"
              id="navCamButton"
              onClick={this.camStatusChanged}
            >
              {localUser !== undefined && localUser.isVideoActive() ? (
                <Videocam />
              ) : (
                <VideocamOff sx={{ color: '#dc0000' }} />
              )}
            </IconButton>

            <IconButton sx={{ color: '#f39c12' }} className="navButton" onClick={this.screenShare}>
              {localUser !== undefined && localUser.isScreenShareActive() ? <PictureInPicture /> : <ScreenShare />}
            </IconButton>

            {localUser !== undefined && localUser.isScreenShareActive() && (
              <IconButton onClick={this.stopScreenShare} id="navScreenButton">
                <StopScreenShare color="secondary" />
              </IconButton>
            )}

            <IconButton sx={{ color: '#f39c12' }} className="navButton" onClick={this.switchCamera}>
              <SwitchVideoIcon />
            </IconButton>
            <IconButton sx={{ color: '#f39c12' }} className="navButton" onClick={this.toggleFullscreen}>
              {localUser !== undefined && this.state.fullscreen ? <FullscreenExit /> : <Fullscreen />}
            </IconButton>
            <IconButton
              sx={{ color: '#dc0000' }}
              className="navButton"
              onClick={(this.camStatusChanged, this.leaveSession)}
              id="navLeaveButton"
            >
              <PowerSettingsNew />
            </IconButton>
            <IconButton sx={{ color: '#f39c12' }} onClick={this.toggleChat} id="navChatButton">
              {this.props.showNotification && <div id="point" className="" />}
              <Tooltip title="Chat">
                <QuestionAnswer />
              </Tooltip>
            </IconButton>
          </div>
        </Toolbar>
      </AppBar>
    );
  }
}
