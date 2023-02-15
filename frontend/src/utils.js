import {
  AccountBoxOutlined,
  AddHomeOutlined,
  ReportProblemOutlined,
  VolunteerActivismOutlined,
} from '@mui/icons-material';

// props로 전달되는 icon에 따라 다른 icon 컴포넌트 리턴
export function utilGetSideMenuIcon(icon) {
  switch (icon) {
    case 'mypage':
      return <AccountBoxOutlined />;
    case 'shelter':
      return <AddHomeOutlined />;
    case 'report':
      return <ReportProblemOutlined />;
    case 'volunteer':
      return <VolunteerActivismOutlined />;
    default:
      return;
  }
}
